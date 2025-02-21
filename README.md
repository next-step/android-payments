# android-payments
https://www.figma.com/design/OhrMuSgyoqk6nBty3BBA1u/%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-Compose-%EB%AF%B8%EC%85%98-%EB%94%94%EC%9E%90%EC%9D%B8?node-id=508-360&t=YaxCeuHiG9Tgq4Pw-0

# step1
- [x] 카드 화면 UI 구현

# step2
- [x] 카드 목록화면 구현
  - LazyColumn 이용
- [x] 카드 목록 -> 카드 추가 화면 이동
- [ ] 새로운 카드 추가 시, 카드 목록 업데이트
  - ViewModel, StateFlow 이용
- [x] 카드 목록 isEmpty 일 경우, 안내 문구 노출
  - 새로운 카드를 등록해주세요
- [x] 카드 목록 아이템 1개 이하일 경우, 카드 추가 UI 아이템으로 노출
- [ ] 카드 목록 아이템 2개 이상일 경우, 카드 추가 버튼 탑바에 노출
- [x] 백버튼 구현