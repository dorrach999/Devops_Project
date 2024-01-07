#1st Stage
FROM node:14-alpine3.17 as build-stage
WORKDIR /app
COPY package.json /app/
RUN npm install
COPY . /app/
RUN npm run build

# 2nd Stage
FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build-stage /app/dist/gestion-taches /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]