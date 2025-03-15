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

### Step3 - 페이먼츠(카드사)
- 카드 추가 화면에 접속했을 때 카드사를 필수로 선택해야 한다.
- 선택한 카드사에 따라 카드 미리보기가 바뀌어야 한다.
- (선택사항) 카드사를 선택할 때 적절한 카드사 아이콘을 노출한다.
- 카드사 선택 기능 구현시 FlowRow와 ModalBottomSheet를 활용한다.

#### Step3 - 개선 사항
- BankLogo 컴포넌트 Image 컴포넌트로 대체
- AppContainer, PaymentCard Size 상수로 추출 및 적용
- NewCardScreen 등록 가능 유효성 체크 로직 ViewModel로 이동
- BankSelectRow 1, 2번째 행 열 맞춤
- bankType.bankImageRes!! > bankType.bankImageRes 되도록 로직 수정
- BankBottomModalSheet 뒤로 가기 방지

### Step4 - 페이먼츠(카드 수정)
- 카드 수정 기능을 구현한다.
  - 카드 목록에서 카드를 선택하면 카드 수정 화면으로 이동한다.
  - 카드 수정 화면에서 변경사항이 발생하지 않으면 수정이 불가능하다.
  - 카드가 수정되면 카드 목록 화면에 변경사항이 반영된다.
