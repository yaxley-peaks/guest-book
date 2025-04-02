FROM node:lts AS fe-build

WORKDIR /app

# VITE bakes in envars at prod time.
# hence this cannot be provided via docker-compose
ENV VITE_BASE_API_URL="http://localhost:8080"

COPY frontend/package.json .
COPY frontend/package-lock.json .

RUN npm i

COPY frontend .

RUN npm run build

CMD ["true"]

FROM nginx:latest AS nginx

COPY --from=fe-build /app/dist/ /usr/share/nginx/html

EXPOSE 80/tcp
ENTRYPOINT ["nginx", "-g", "daemon off;"]
