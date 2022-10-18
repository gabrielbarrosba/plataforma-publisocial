package publi.social.springboot2.requests;

import lombok.Data;

@Data
public class MultPostRequestBody {
    private String name;
    private String category;
    private String date;
    private String local;
    private String description;
}
