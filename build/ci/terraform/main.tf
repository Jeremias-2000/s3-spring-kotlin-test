provider "aws" {
  region = "us-east-1"
  access_key = "jeremias-test"
  secret_key = "12345678"
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true
  //
  endpoints {
    s3 = "http://0.0.0.0:4566"
  }
}

resource "aws_s3_bucket" "b" {
    bucket = var.bucket_name_value
}

