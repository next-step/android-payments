# android-payments

https://www.figma.com/design/OhrMuSgyoqk6nBty3BBA1u/%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-Compose-%EB%AF%B8%EC%85%98-%EB%94%94%EC%9E%90%EC%9D%B8?node-id=508-360&t=YaxCeuHiG9Tgq4Pw-0

# step1

- [x] 카드 화면 UI 구현

# step2

- [x] 카드 목록화면 구현
    - LazyColumn 이용
- [x] 카드 목록 -> 카드 추가 화면 이동
- [x] 새로운 카드 추가 시, 카드 목록 업데이트
    - ViewModel, StateFlow 이용
- [x] 카드 목록 isEmpty 일 경우, 안내 문구 노출
    - 새로운 카드를 등록해주세요
- [x] 카드 목록 아이템 1개 이하일 경우, 카드 추가 UI 아이템으로 노출
- [x] 카드 목록 아이템 2개 이상일 경우, 카드 추가 버튼 탑바에 노출
- [x] 백버튼 구현

# step3

- [x] 카드사 선택 UI 구현
    - [x] 선택 가능한 카드사 UI FlowRow
    - [x] 화면 진입 시, 카드사 선택 ModalBottomSheet 로 노출
- [x] 카드사 필수로 선택
- [x] 선택한 카드에 따라 미리보기 변경
- [x] 입력값 포맷팅

# step4

- [ ] 카드 수정 기능 구현
    - [ ] 카드 목록에서 카드 선택 시, 수정 화면으로 이동
        - [x] CardAddActivity -> CardAddModifyActivity
        - [x] 카드 선택 시, CardAddActivity 에 Card의 id 를 파라미터로 전달
        - [ ] CardAddModify 에서, Card id 에 값이 있다면, Card 정보 repository 에서 호출
        - [ ] 받아온 카드 정보 입력창에 설정
        - [ ] 수정으로 진입 시, 상단 체크버튼 수정 버튼으로 변경
        - [ ] 수정 버튼 클릭 시, Repository 에 수정 사항 업데이트
          - [ ] updated 필드 업데이트
    - [ ] 변경 사항이 없다면 수정 불가능
        - [ ] 화면 진입 시 받아온 카드 정보와, 수정 하려는 카드 동등성 확인
          - [ ] updated 필드는 동등성 비교에서 제외
    - [ ] 카드 수정사항이 카드 목록에 반영