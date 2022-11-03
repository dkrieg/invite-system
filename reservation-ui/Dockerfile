FROM node:latest as build
WORKDIR /usr/local/app
COPY ./ /usr/local/app/
RUN npm install
RUN npm run build
FROM steebchen/nginx-spa:stable
COPY --from=build /usr/local/app/dist/invited /app
EXPOSE 80
CMD ["nginx"]