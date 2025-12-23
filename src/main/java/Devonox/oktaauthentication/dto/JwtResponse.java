package Devonox.oktaauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter
public class JwtResponse {
    private String token;

    public JwtResponse(String s) {
        this.token=s;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
