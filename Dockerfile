FROM nginx:latest
LABEL authors="yaxley peaks"

COPY frontend/dist/ /usr/share/nginx/html
RUN mkdir /usr/share/nginx/html/assets
COPY frontend/dist/ /usr/share/nginx/html/assets

EXPOSE 80/tcp
ENTRYPOINT ["nginx", "-g", "daemon off;"]
