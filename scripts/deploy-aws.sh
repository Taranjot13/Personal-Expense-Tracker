#!/bin/bash
# Deploy application to AWS

set -e

echo "🚀 Deploying Expense Tracker to AWS..."

# Variables
AWS_REGION=${AWS_REGION:-us-east-1}
ECR_REPO_NAME=${ECR_REPO_NAME:-expense-tracker}
IMAGE_TAG=${IMAGE_TAG:-latest}

# Get AWS account ID
AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
ECR_URI="${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}"

echo "📦 Building Docker image..."
docker build -t ${ECR_REPO_NAME}:${IMAGE_TAG} .

echo "🔐 Logging into AWS ECR..."
aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_URI}

echo "🏷️ Tagging image for ECR..."
docker tag ${ECR_REPO_NAME}:${IMAGE_TAG} ${ECR_URI}:${IMAGE_TAG}

echo "📤 Pushing image to ECR..."
docker push ${ECR_URI}:${IMAGE_TAG}

echo "✅ Deployment completed!"
echo "Image pushed to: ${ECR_URI}:${IMAGE_TAG}"
