FROM node:14-alpine as build-stage

WORKDIR /app

COPY package*.json  ./

# 先安装依赖
RUN npm install

# 拷贝源代码
COPY . .

# 编译生产环境代码
RUN npm run build


FROM nginx as nginxpro
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 8081
CMD ["nginx", "-g", "daemon off;"]