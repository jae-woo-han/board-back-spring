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
- GET /api/boards/{게시판 id}: 게시판에 속한 게시글 조회
- GET /api/posts/{post id}: 게시글 조회
- GET /api/comments/{post id}: 게시글에 속한 댓글 조회
- POST /api/posts: 게시글 등록
- POST /api/comments: 댓글 등록

