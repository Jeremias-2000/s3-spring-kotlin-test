# Project Setup and Deployment

This project utilizes **Terraform** to manage AWS resources, specifically an **S3 bucket**. The infrastructure is simulated locally using **LocalStack**.

## Prerequisites

Before running the code, ensure you have the following tools installed:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Terraform](https://www.terraform.io/downloads)

## Setup Instructions

Follow the steps below to set up and deploy the infrastructure.

### 1. Build Docker Containers

To start LocalStack and the required services, run the following command:

```bash
docker-compose up --build
```

This will build the necessary Docker containers and start LocalStack, which simulates AWS services locally.

### 2. Initialize Terraform

Once the containers are up and running, initialize the Terraform environment:

```bash
terraform init
```

This will download the required Terraform providers and set up the environment.

### 3. Validate Terraform Configuration

Before applying any changes, it's a good practice to validate the configuration to ensure there are no syntax or configuration errors. Run:

```bash
terraform validate
```

This command checks the configuration for potential issues, so you can fix them before applying.

### 4. Apply Terraform Configuration

Now, apply the Terraform configuration to create the S3 bucket (simulated with LocalStack):

```bash
terraform apply
```

Terraform will prompt you for confirmation. Type `yes` to proceed with the deployment.

### 5. Done!

That's it! You now have your S3 bucket created, and LocalStack is simulating the AWS services locally.

![Screenshot From 2025-01-29 14-32-52](https://github.com/user-attachments/assets/422c5a84-3376-4428-8aa7-4bec074fc064)
![Screenshot From 2025-01-29 15-24-37](https://github.com/user-attachments/assets/c6729f45-3a32-4a7d-a776-1d768c7e72fc)
![Screenshot From 2025-01-29 15-56-29](https://github.com/user-attachments/assets/b54a759b-0a44-4de4-9d0b-54b682444410)
![Screenshot From 2025-01-29 16-09-19](https://github.com/user-attachments/assets/fc28c60e-05c6-4703-a285-27e7b8400587)

---

Let me know if you need further assistance or any additional changes!