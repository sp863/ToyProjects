# ToyProjects

## 2인 플레이 체스 게임

### 체스게임 설명 및 기능 요구사항
- 가로와 세로 각각 8줄씩 (8x8) 64칸으로 된 게임판에서 두 명의 플레이어가 말들의 규칙에 따라 움직여 상대방의 킹을 잡기 직전의 상황을 만들면 승리하는 게임
- '2인 플레이 체스 게임'은 컴퓨터와 대결하는 체스게임이 아닌 2명의 플레이어가 각자 움직을 입력하여 플레이하는 게임 프로그램
- 일반적인 체스 게임 Rule 과 Convention 을 최대한 따르 도록 설계

- 말들의 종류 및 움직임 규칙
  - 플레이어 색깔별로 16개씩 총 32개
  - 하얀색 플레이어가 항상 선으로 공격을 함. Player White
  - Rook (R) : 다른 말이 길을 막지 않는 한, 가로세로 방향으로 몇 칸이든 이동할 수 있음.
  - Bishop (B) : 다른 말이 길을 막지 않는 한, 대각선 방향으로 몇칸이든 이동할 수 있음.
  - Queen (Q) : 다른 말이 길을 막지 않는 한, 가로세로 대각선 방향으로 몇칸이든 일직선 이동할 수 있음 (Rook과 Bishop의 움직임을 합친 개념)
  - King (K) : 가로세로 대각선으로 한칸 이동. 다른 말처럼 상대 말을 공격해 잡을 수 있지만 상대의 킹을 잡을 수는 없음.
  - Knight (N) : 가로 혹은 세로 방향으로 한 칸 이동한 뒤 대각선으로 한칸 이동. 이동하려는 길에 다른 말이 있어도 뛰어넘어서 이동 가능.
  - Pawn (P) : 폰은 앞으로만 이동. 옆이나 뒤, 또는 대각선으로 이동할 수 없음. 하지만 상대 말을 잡을때는 앞쪽 대각선으로만 잡을 수 있음. 한번도 움직이지 않은 폰은 앞쪽으로 한칸이나 두칸을 자유롭게 이동 가능하지만 한 번 이상 이동한 폰은 한칸씩만 전진 할 수 있음.

- 특별 규칙
  - Promotion : 폰이 전지을 계속해 게임판 반대쪽 끝에 도착 하면 폰을 퀸, 룩, 비숍, 나이트 가운데 하나로 바꿀 수 있음.
  - Castling : 아래 4개의 제약 조건이 없다면, 킹의 위치와 룩의 위치를 조정해 킹이 성안으로 들어가 안전하게 대피하는 상황을 만들 수 잇음.
    - King Side Castling : 킹이 오른쪽으로 두 칸 가고, 룩이 킹을 뛰어 넘어 킹 옆으로 옴
    - Queen Side Castling : 킹이 왼쪽으로 두 칸 가고, 룩이 킹을 뛰어넘어 킹 옆으로 옴
    - 4가지 제약 조건 (캐슬링이 허용되지 않는 경우)
      - 킹과 룩 사이에 다른 말이 있을때.
      - 킹이나 룩이 움직였을 때.
      - 킹이 상대의 말에게 직접 (체크) 당하고 있을때.
      - 킹이 캐슬링으로 지나가는 칸이 상대 공격권 내에 있을 때.
  - En Passant : 한번도 움직이지 않은 폰이 상대 폰에게 잡히지 않으려고 두칸을 전진할 때도 이 폰을 잡을 수 있게 하는 규칙

- 체크 : 킹을 공격하는 상태를 체크라고 함.
  - 킹을 공격하고 있는 상대 말을 잡아서 피하거나
  - 킹을 공격하고 있는 상대 말의 진로를 막거나
  - 킹이 움직여 상대의 공격을 피해야 함
- 체크메이트 : 체크가 된 상태에서 이 상태를 해결할 수 없을 때 체크메이트라고 함. 킹을 잡을 수 없기 때문에 체크메이트 당한 쪽이 킹을 스스로 쓰러드려 진것을 인정하는 것으로 게임이 끝남

### 체스게임 플로우
- 메인메뉴에서 Play Game을 선택
- 하얀색 플레이어 선 공격으로 하고 다음턴은 검정색 플레이어로 자동으로 순서가 돌아감
- 본인턴 일때 플로우 방식
  - 체스 Unit 명(예: P1)을 입력
  - 위치 입력 (예: a1)을 입력
  - 특별 규칙이 가능 할 시 Promotion, Castling 을 시행할 수 있음
- 입력 받은 Unit명이나 위치명이 가능한지 검사
- 상대방의 말의 위치로 가서 상대방의 말을 뺐으면 상대방의 Alive Unit 목록에서 그 말은 빠지고 뺐은 플레이어의 Taken Unit 목록으로 추가됨
- 턴이 끝날때 현재 상대방의 King을 체크한 상황인지 검사하고 공격 범위에 있을 시 Check 출력
- 턴이 모두 끝나면 Check Mate 를 확인 후 게임 종료 여부를 확인
- Check Mate 상황이 확인 되었을 시 게임 종료

### 체스게임 기능 목록
- 입력
  - [ ] Unit 명 2자리 입력
  - [ ] 체스판 위치(타일)명 2자리 입력
  - 특별 규칙 일 시
    - Pawn Promotion
      - [ ] 어떤 말로 폰을 승진 시킬 지 입력
    - Castling (가능 할 시 King이나 Rook을 선택 할 시)
      - [ ] Castling을 시행 할건지 말건지 입력
      - [ ] Queen Side Castling / King Side Castling 선택 (하나만 가능 할 시 Castling 을 시행하는 동시에 자동으로 가능 한 쪽으로 선택)
- 출력
  - [ ] 메인메뉴 출력
  - [ ] 현재 Turn 수 출력
  - [ ] 각 플레이어의 뺏은 말 목록 출력
  - [ ] 플레이어의 시점에서의 체스보드 출력 / 매 턴마다 체스보드는 180도 회전 (예: 하얀색 플레이어 턴 일때는 시작 시 하얀색 말들의 위치에 따라 아래에 위치하게 체스보드를 출력)
  - [ ] 체스보드의 위치 인덱스(타일 위치명) 출력 (예: a b c d e f g h (가로) 1 ~ 8 (세로))
  - [ ] 체스보드 위에서의 말들의 이동 모두 출력
  - [ ] 상대방의 King 에게 Check상황을 만들었을 시 Check 출력
  - [ ] 승자 출력
- 체스게임
- 체스게임 컨트롤러
  - 게임의 시작과 종료 처리
  - 플레이어 턴 관리 및 게임 종료 조건 확인
- 플레이어
- 플레이어 매니저
- 비교 및 확인
