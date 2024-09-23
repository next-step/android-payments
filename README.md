# android-payments

## 🚀 1단계 - 페이먼츠(카드 추가)

### 구현 기능 목록
- [x] 사전 코드를 가능한 Stateless 컴포넌트로 리팩터링하여 테스트 가능하도록 구현

## 🚀 2단계 - 페이먼츠(카드 목록)

### 구현 기능 목록
- [x] 리뷰 반영
  - [x] 문자열, 색상은 리소스로 관리
- [x] 카드 목록 화면 구현
  - [x] 새로운 카드가 추가되었을 때 카드 목록이 업데이트 되어야 한다.
  - [x] 카드 목록이 비어있을 때에는 "새로운 카드를 등록해주세요" 안내가 노출되어야 한다.
  - [x] 카드 목록에 카드가 한 개 있을 때의 카드 추가 UI는 목록 하단에 노출된다.
  - [x] 카드 목록에 카드가 여러 개 있을 때의 카드 추가 UI는 상단바에 노출된다.

## 🚀 3단계 - 페이먼츠(카드사)

### 구현 기능 목록
- [ ] 리뷰 반영
  - [x] 카드 리스트 프리뷰 파라미터에 실 데이터 추가
  - [x] PaymentCardLayout 추가
  - [x] TextField의 VisualTransformation 사용해서 카드번호, 만료날짜 포맷 개선
  - [x] cardNumber의 뒷자리 8자리 * 처리
- [ ] 카드 목록 화면 구현
  - [ ] 카드 추가 화면에 접속했을 때 카드사를 필수로 선택해야 한다.
  - [ ] 선택한 카드사에 따라 카드 미리보기가 바뀌어야 한다.
  - [ ] (선택사항) 카드사를 선택할 때 적절한 카드사 아이콘을 노출한다.
