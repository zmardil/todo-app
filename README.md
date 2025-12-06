# todo-app
A simple full-stack todo application (Spring Boot + React + PostgreSQL)

Run the whole application with Docker Compose (builds backend/frontend and starts Postgres):

```bash
docker-compose up --build
```

After startup:

- Frontend: http://localhost:3000
- Backend API: http://localhost:8080/api/tasks

Notes:
- Backend: `backend/` (Java + Spring Boot)
- Frontend: `frontend/` (React + Vite, built into nginx)
- Database: Postgres (docker-compose)

See the repository files for implementation and tests.
