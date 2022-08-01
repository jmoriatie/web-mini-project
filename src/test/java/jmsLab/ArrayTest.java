package jmsLab;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class ArrayTest {

    @Test
    void arrayTest(){
        ArrayList<Integer> testArray = new ArrayList<>();

        System.out.println(testArray.size());
        assertThat(testArray.size()).isEqualTo(0);
    }
}
