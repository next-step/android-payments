# android-payments

## 1단계
- NewCardScreen UI 구현

## 2단계
- 카드 데이터 저장소 구현
- NewCardScreen 구조 리팩토링
- CardListScreen 껍데기 구현
- 카드 추가 버튼 UI 구현
- 카드 내용 UI 구현
- 카드가 없는 케이스 화면 구현
- 카드가 n 개인 케이스 화면 구현
- CardListScreen 기능 구현
  - 카드 목록이 비어있을 때에는 "새로운 카드를 등록해주세요" 안내가 노출되어야 한다.
  - 카드 목록에 카드가 한 개 있을 때의 카드 추가 UI는 목록 하단에 노출된다.
  - 카드 목록에 카드가 여러 개 있을 때의 카드 추가 UI는 상단바에 노출된다.
  - 카드 추가 버튼 클릭 시, 카드 추가 화면으로 이동한다

## 3단계
- [x] 은행 타입 정의 및 Card 모델에 필드 추가
- [x] 카드 생성 화면에 바텀 시트 추가
- [x] 바텀 시트 UI 구현
- [x] 카드 생성에 은행 고를 수 있는 기능 구현

## 4단계
- [] 카드 수정 페이지 추가
- [] 카드 클릭시 수정 페이지로 이동 구현
- [] 카드 수정 화면에서 변경사항이 발생하지 않으면 확인 버튼 disable 처리
- [] 카드가 수정되면 카드 목록 화면에 변경사항 반영