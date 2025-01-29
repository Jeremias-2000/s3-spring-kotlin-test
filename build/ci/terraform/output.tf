output "s3_url" {
  value = "http://localhost:4566/${aws_s3_bucket.b.id}"
}