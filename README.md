# android-payments

### Step1 - 페이먼츠(카드 추가)
- 카드 추가 뷰를 구현한다.
- 비밀번호는 노출되어서는 안된다.

### Step2 - 페이먼츠(카드 목록)
- 카드 목록 화면을 구현하여 카드 추가 화면과 연결한다.
  - 새로운 카드가 추가되었을 때 카드 목록이 업데이트 되어야 한다.
- 카드 목록 상태에 따른 UI 변경사항을 노출한다.
  - 카드 목록이 비어있을 때에는 "새로운 카드를 등록해주세요" 안내가 노출되어야 한다.
  - 카드 목록에 카드가 한 개 있을 때의 카드 추가 UI는 목록 하단에 노출된다.
  - 카드 목록에 카드가 여러 개 있을 때의 카드 추가 UI는 상단바에 노출된다.

#### Step2 - 페이먼츠(카드 목록) 개선 사항
- vararg를 활용해 카드 등록 함수 개선
- CardUiState object Empty > data object 타입으로 수정
- CardApp 사용하지 않는 코드 제거
- navigation route String 에서 class type 으로 수정
- NewCardScreen coroutineScope 파라미터 내부에서 처리하도록 수정
- hardcode String 을 string resource로 대체
- NewCardScreen 입력 필드 유효성 체크 로직 NewCardTopBar 내부에서 외부로 상태 호이스팅
- 컨벤션 가이드에 따른 modifier 순서 변경
- CardNumberRow return 제거 및 UI 호출 로직 수정
