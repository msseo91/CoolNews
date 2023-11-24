# CoolNews

* 디자인패턴은 MVVM 을 사용하였습니다.
* 가로길이에 따라 다른 갯수의 row 를 사용하라고 하였으나 문맥상 column 이 맞는것 같아 column 으로 구현하였습니다.
* '오프라인 상태' 는 해당 상태일 때 network API 가 실패하니, 해당 상황으로 구현하였습니다.
* urlToImage 의 로컬 사용은 coil 의 disk cache 를 사용하였습니다.