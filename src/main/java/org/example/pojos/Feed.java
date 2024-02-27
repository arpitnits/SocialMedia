package org.example.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Feed {

    private String feedId;

    private String uuid;

    private String text;

    private String imgUrl;
}
