# syntax=docker/dockerfile:1
FROM node:14-alpine as build-stage

WORKDIR /app

COPY package*.json  ./

# 先安装依赖
RUN npm install

# 拷贝源代码
COPY . .

# 编译生产环境代码
RUN npm run build

CMD ["npm","run","dev"]