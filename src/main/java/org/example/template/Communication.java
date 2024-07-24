package org.example.template;

import org.example.template.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;


@Component("communication")
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public List<String> cookies = new ArrayList<>();

    String result = "";

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        cookies = responseEntity.getHeaders().get("Set-Cookie");
        List<User> users = responseEntity.getBody();
        return users;
    }

    public void saveUser(User user) {
        Long id = user.getId();
        HttpHeaders headers = new HttpHeaders();
        if (id > 2) {
            headers.put(HttpHeaders.COOKIE, cookies);
            HttpEntity<User> entity = new HttpEntity<>(user, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
            result = result + responseEntity.getBody();
        }
    }

    public void updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        restTemplate.put(URL, entity);
        result = result + responseEntity.getBody();
    }

    public void deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        result = result + responseEntity.getBody();
    }
}
