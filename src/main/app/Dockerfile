# ----------------------------------------------------------------------------------------------------------------------
#
#     ____             __             _____ __
#    / __ \____  _____/ /_____  _____/ __(_) /__
#   / / / / __ \/ ___/ //_/ _ \/ ___/ /_/ / / _ \
#  / /_/ / /_/ / /__/ ,< /  __/ /  / __/ / /  __/
# /_____/\____/\___/_/|_|\___/_/  /_/ /_/_/\___/
#
#
# For official documentation, see:
# https://docs.docker.com/engine/reference/builder/
#
# ----------------------------------------------------------------------------------------------------------------------

# ----------------------------------------------------------------------------------------------------------------------
# BUILD ENVIRONMENT
#
# Description:
# Set up environment to create production ready React application
#
# For official documentation, see:
# https://docs.docker.com/develop/develop-images/multistage-build
#
# ----------------------------------------------------------------------------------------------------------------------

# Set base image
# See available options on https://hub.docker.com/_/node
FROM node:14.7-alpine AS builder


# Set arguments which can be later overwritten higher in hierarchy with ex. Docker Compose, or .gitlab-ci.yml
ARG VERSION=0.0.1
ARG NODE_ENV=production
ARG PORT=80
ARG BASE_URL=http://127.0.0.1:3000

# Set environmental variables from arguments
ENV VERSION=$VERSION
ENV NODE_ENV=$NODE_ENV
ENV PORT=$PORT
ENV BASE_URL=$BASE_URL

# Install required packages
RUN apk update && apk add git && apk add -U tzdata && \
  cp /usr/share/zoneinfo/Europe/Helsinki /etc/localtime

# Set working directory
WORKDIR /opt/tarinamittausUI

# Copy files from ./ to WORKDIR
COPY . .

# Set /node_modules/.bin to $PATH variable
ENV PATH /opt/tarinamittausUI/node_modules/.bin:$PATH

# Install required dependencies inside the container
RUN npm install
RUN npm cache clean --force
RUN webpack --config webpack.$NODE_ENV.ts --mode $NODE_ENV

# Copy Apache .htaccess file to serve index.html on all routes (i.e. enable React Router)
# https://angular.io/guide/deployment#routed-apps-must-fallback-to-indexhtml
COPY .htaccess dist/

# ----------------------------------------------------------------------------------------------------------------------
# SERVER ENVIRONMENT
#
# Description:
# Set up environment to run production ready React application
#
# For official documentation, see:
# https://docs.docker.com/develop/develop-images/multistage-build
#
# ----------------------------------------------------------------------------------------------------------------------

# See available Apache base images at https://hub.docker.com/_/httpd
FROM httpd:2.4-alpine

# Copy optimized React prodcution build to be served by the Apache
COPY --from=builder /opt/tarinamittausUI/dist /usr/local/apache2/htdocs/

# Set working directory to Apache server root /htdocs
WORKDIR /usr/local/apache2/htdocs/

# Allow Apache to read .htaccess files located under the /usr/local/apache2/htdocs/ directory
RUN sed -i 's|AllowOverride none|AllowOverride all|g' /usr/local/apache2/conf/httpd.conf
RUN sed -i 's|AllowOverride None|AllowOverride all|g' /usr/local/apache2/conf/httpd.conf

# Enabling deflate module in Apache
RUN sed 's/#LoadModule deflate_module modules\/mod_deflate.so/LoadModule deflate_module modules\/mod_deflate.so/' \
  /usr/local/apache2/conf/httpd.conf > /usr/local/apache2/conf/httpd.conf.deflate

# Enabling rewrite module in Apache (required)
RUN sed 's/#LoadModule rewrite_module modules\/mod_rewrite.so/LoadModule rewrite_module modules\/mod_rewrite.so/' \
  /usr/local/apache2/conf/httpd.conf.deflate > /usr/local/apache2/conf/httpd.conf

# Validate Apache configuration file
RUN httpd -t

HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:$PORT || exit 1

