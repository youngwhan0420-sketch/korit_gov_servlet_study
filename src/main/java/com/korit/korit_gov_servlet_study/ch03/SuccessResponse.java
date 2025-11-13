//package com.korit.korit_gov_servlet_study.ch03;
//
//import lombok.Builder;
//
//@Builder
//public class SuccessResponse<T> { //1. 조회한 결과를 이거를 통해서 응답을 해줄껀데 조회된 결과가 타입이 뭔지 모른다. 그러면 얘 멤버변수 타입을
//    private int status = 200;
//    private String message;
//    private T body; //제네릭타입으로 써준다. 뭐가 올지 몰라서 그떄가서 타입을 지정해주기 위해
//    //이제 유저 레퍼지토리로가서 담아준다.
//}
package com.korit.korit_gov_servlet_study.ch03;

import lombok.Builder;

@Builder
public class SuccessResponse<T> {
    private int status = 200;
    private String message;
    private T body;
}