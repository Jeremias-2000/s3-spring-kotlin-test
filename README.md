

# Project Setup and Deployment

This project uses Terraform to manage AWS resources, specifically an S3 bucket. The infrastructure is simulated using LocalStack.

## Prerequisites

Before running the code, ensure you have the following tools installed:
- Docker
- Docker Compose
- Terraform

## Setup Instructions

### 1. Build Docker Containers

To start LocalStack and the required services, run the following command:

```bash
docker-compose up --build
```

This will build the necessary containers and start LocalStack.

### 2. Initialize Terraform

Once the containers are up and running, initialize the Terraform environment:

```bash
terraform init
```

This command will download the necessary providers and set up the Terraform environment.

### 3. Apply Terraform Configuration

Now, apply the Terraform configuration to create the S3 bucket (simulated with LocalStack):

```bash
terraform apply
```

Confirm the action by typing `yes` when prompted.

### 4. Done!

That's it! You now have your S3 bucket created, and LocalStack is simulating the AWS services locally.

---

Let me know if you want to add anything else!
