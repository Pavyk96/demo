data "archive_file" "log_receiver_zip" {
  type        = "zip"
  source_dir  = "${path.module}/function"
  output_path = "${path.module}/log-receiver.zip"
}

resource "yandex_function" "log_receiver" {
  name        = "log-receiver"
  description = "Принимает и логирует JSON"

  user_hash = data.archive_file.log_receiver_zip.output_sha256

  runtime           = "python311"
  entrypoint        = "index.handler"
  memory            = "128"
  execution_timeout = "3"

  service_account_id = "ajes70okd947b5rgde1d"
  folder_id          = "b1gg6budajcjd7gpjohb"

  content {
    zip_filename = data.archive_file.log_receiver_zip.output_path
  }
}
