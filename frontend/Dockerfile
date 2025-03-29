FROM node:lts AS build
LABEL authors="yaxley peaks"

WORKDIR /app

COPY package.json .
COPY package-lock.json .

RUN npm i

COPY . .

RUN npm run build

CMD ["true"]

FROM scratch
COPY --from=build /app/dist/* /