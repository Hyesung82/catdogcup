# catdogcup (개냥컵)
고양이 &amp; 강아지 월드컵입니다.


## [WorkLog]

<details>
<summary>2024.06.07</summary>


## 1. 데이터 클래스의 통합 이슈
아래의 경우 통합 관련
CatBreedsDto + DogBreedsDto -> BreedsDto
CatImageDto + DogImageDto -> ImageDto

- Dto 자체를 통합해서 각각의 API를 상속받아서 사용하려고 하니까, 오히려 구조가 깔끔하지 않고 상속 때문에 개발이 복잡해지는 문제가 발생
- 과연 굳이 클래스를 하나로 통합하고 상속받아서 사용하는 것이 나을지, 각 API는 결국 다른 구조의 API의 가능성이 있기에 그대로 따로 사용하는 것이 나을지 논의 피료

## 2. 모듈의 세부적인 분리
기존 data 레이어는 임시적으로 catApi만 부르고 있다.
그런데 모듈을 분리하는 이유는 결국 api가 변경되거나 추가될 때 좀 더 효율적으로 해당 base 도메인의 api를 관리하기 위해
분리하는 것이다. 따라서 각 api 데이터 모듈의 이름도 명확할 필요가 있다고 생각했다.
data -> catData / dogData

</details>