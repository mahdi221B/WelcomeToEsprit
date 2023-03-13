package tn.esprit.spring.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private String text;
    private String to;
}
