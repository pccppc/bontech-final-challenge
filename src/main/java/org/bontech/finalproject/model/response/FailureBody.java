package org.bontech.finalproject.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FailureBody<E> {
    private final boolean success = false;
    private E errors;
}
