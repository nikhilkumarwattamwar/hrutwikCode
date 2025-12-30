package Devonox.oktaauthentication.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter@Setter
public class JwtResponse {
    private String token;

    public JwtResponse(String s) {
        this.token=s;
    }


}
