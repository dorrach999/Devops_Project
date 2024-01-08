# Devops_Project
# Jenkins Pipeline for Docker Image Build and Kubernetes Deployment

## Overview

This repository contains a Jenkins pipeline for automating the build and deployment process. The pipeline is designed to handle the following aspects:

1. **Initialization of Global Variables**
   - Set up global variables required for the pipeline execution.

2. **Docker Image Build**
   - Build Docker image for the application.

3. **Testing**
   - Perform unit or integration testing to ensure the application's functionality.

4. **Push to Image Registry**
   - Push the built Docker image to a container registry such as DockerHub, ACR (Azure Container Registry), or ECR (Amazon Elastic Container Registry).

5. **Cleanup**
   - Perform cleanup tasks to ensure a clean environment after the pipeline execution.

## Prerequisites

Before running the pipeline, ensure that the following prerequisites are met:

- Jenkins server is set up and configured.
- Docker is installed on the Jenkins server.
- Kubernetes cluster is available for deployment.

## Pipeline Steps

### Step 1: Initialization of Variables
   - Initialize global variables required for the pipeline execution.

### Step 2: Docker Image Build
   - Build the Docker image for the application.

### Step 3: Testing
   - Execute unit or integration tests to verify the application's functionality.

### Step 4: Push to Image Registry
   - Push the built Docker image to a specified container registry.

### Step 5: Cleanup
   - Clean up resources and environment after the pipeline execution.

## Kubernetes Deployment

The project includes Kubernetes manifests for deployment with the following components:

1. **RollingUpdate Deployment**
   - Deploy the application using a RollingUpdate strategy.
   - Replicaset configured with 5 replicas for high availability.

2. **NodePort Service (for Minikube Testing)**
   - Expose the application using a NodePort service for testing on Minikube.

3. **LoadBalancer Service (for Production Deployment)**
   - Expose the application using a LoadBalancer service for production deployment on AKS (Azure Kubernetes Service) or EKS (Elastic Kubernetes Service).

## Usage

1. Configure Jenkins to connect to your source code repository.
2. Create a new Jenkins pipeline job and link it to this repository.
3. Trigger the pipeline manually or set up webhooks for automatic triggering on code changes.

**Note:** Ensure that the necessary credentials for the container registry and Kubernetes cluster are configured in Jenkins.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to modify and distribute it as needed.