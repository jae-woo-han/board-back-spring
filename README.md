# board-back-spring
- 간단한 회원제 게시판 API 서버 구축

## 사용 기술
언어 및 라이브러리 : Java 17, Spring-Boot  
데이터베이스 : h2(테스트 용도)
인증 : JWT, Spring security

## 기능 설명
- 게시글 목록 > 게시글 > 댓글 형식의 한국에서 많이 쓰이는 형식의 게시판
- 회원가입, 로그인/아웃, 게시글 조회/등록/수정/삭제, 댓글 조회/등록/수정/삭제
- 게시글, 댓글의 등록/수정/삭제는 회원 및 관리자만 가능하도록 구축

## API 구축
- 게시판: boards
  - GET /api/boards/{게시판 id}/posts: 게시판에 속한 게시글 조회
- 게시글: posts
  - GET /api/posts/{post id}: 게시글 조회
  - POST /api/posts: 게시글 등록
  - PUT /api/posts/{post id}: 게시글 수정
  - DELETE /api/posts/{post id}: 게시글 삭제
- 댓글: comments
  - GET /api/comments/{post id}/comments: 게시글에 속한 댓글 조회
  - POST /api/comments: 댓글 등록
  - PUT /api/comments/{comment id}: 댓글 수정
  - DELETE /api/comments/{comment id}: 댓글 삭제
- 회원: users
  - 가입, 로그인, 회원정보 조회

## Domain 정의
- User
  - 간단한 회원정보
  - 아이디, 비밀번호, 이름
- Board
  - 게시판
  - 게시판명, 게시판 ID
- Post
  - 게시글
  - 제목, 작성자, 내용, 작성일, 게시글 ID
  - 댓글을 List<Comments> 로 포함할까 고민했는데, 고유속성은 아니라고 판단해서 분리, 댓글 조회는 서비스에서 처리
- Comment
  - 댓글
  - 내용, 작성자, 작성일, 속한 게시글 ID

