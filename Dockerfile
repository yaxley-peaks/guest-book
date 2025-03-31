FROM node:lts AS fe-build

WORKDIR /app

ENV BASE_BACKED_URL="https://jsonplaceholder.typicode.com"

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
