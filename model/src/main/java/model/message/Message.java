package model.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class Message implements Serializable {

    private String sender;
    private String body;
    private Map<String, Object> properties;

}
