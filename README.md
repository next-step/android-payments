## STEP-1
- 카드 추가 화면 구현

## STEP-2
- 카드 목록 화면 구현
  - 상단 타이틀 구현
  - 새로운 카드 추가 UI 구현
  - 추가된 카드 UI 구현
- 카드 등록 구현
- 카드 목록 상태에 따른 UI 변경
  - 카드 목록이 비어있을 때에는 "새로운 카드를 등록해주세요" 안내가 노출
  - 카드 목록에 카드가 한 개 있을 때의 카드 추가 UI는 목록 하단에 노출
  - 카드 목록에 카드가 여러 개 있을 때의 카드 추가 UI는 상단바에 노출

### STEP-2 Feedback
- CustomVisualTransformation 개선
- CardListTopBar 개선
- PaymentCardsRepository 변수명 개선
- Credit Card 로직 분리 및 개선
  - UI, 비즈니스 로직 분리
  - braces
- CardListViewModel - fetchCardList 개선
  - when 절 활용
- AdditionCard 및 PaymentCard 개선
  - 확장성 고려 
- NewCardScreen Preview - expiredDate, cardNumber 확인
- NewCardTextField Preview
  - 실제 데이터 넣어서 확인
  - VisualTransformation 관련 요구사항 확인
- CardListScreen Preview 및 테스트
  - UI 기능 관련 테스트 추가
    - ex) 16자리 숫자를 입력했을때 뒤8자리는 *로 보인다.
- 카드 추가 시 화면 이동 테스트 추 (NewCardScreen - cardAdded)

### STEP-2 Feedback #2
- Composable 접근 제어
- PaymentCard 개선
- CustomVisualTransformation 로직 개선
  - filter에서 take 사용
  - originalToTransformed,transformedToOriginal 개선

## STEP-3
- 카드사 선택 BottomSheet 구현
  - 카드사 목록 구현
    - 카드사 목록 아이템 구현
- 카드사 선택 시 해당 카드에 맞게 카드 미리보기 변경 구현


