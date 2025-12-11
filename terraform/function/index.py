import logging
import json
import os
from pythonjsonlogger import jsonlogger

class YcLoggingFormatter(jsonlogger.JsonFormatter):
    def add_fields(self, log_record, record, message_dict):
        super(YcLoggingFormatter, self).add_fields(log_record, record, message_dict)
        log_record['level'] = record.levelname.replace('WARNING', 'WARN').replace('CRITICAL', 'FATAL')
        log_record['logger'] = record.name

logHandler = logging.StreamHandler()
logHandler.setFormatter(YcLoggingFormatter('%(message)s %(level)s %(logger)s'))

logger = logging.getLogger('LogReceiver')
logger.propagate = False
logger.addHandler(logHandler)
logger.setLevel(logging.INFO)

def handler(event, context):
    try:
        http_method = event.get('httpMethod', '')
        body = event.get('body', '')

        if not body:
            logger.warning("Получен запрос с пустым телом.")
            return {'statusCode': 400, 'body': 'Empty request body'}

        log_data = json.loads(body)

        logs = log_data if isinstance(log_data, list) else [log_data]

        for log_entry in logs:
            level = log_entry.get('level', 'INFO').upper()
            message = log_entry.get('message', 'No message provided')
            extra_data = {k: v for k, v in log_entry.items() if k not in ('level', 'message')}

            log_method = getattr(logger, level.lower(), logger.info)
            log_method(message, extra=extra_data)

        logger.info(f"Успешно обработано {len(logs)} записей.", extra={'source': event.get('headers', {}).get('X-Forwarded-For', 'unknown')})
        return {'statusCode': 200, 'body': 'Logs received successfully'}

    except json.JSONDecodeError as e:
        logger.error("Ошибка декодирования JSON.", extra={'error': str(e)})
        return {'statusCode': 400, 'body': 'Invalid JSON format'}
    except Exception as e:
        logger.error("Внутренняя ошибка обработчика.", exc_info=True, extra={'error': str(e)})
        return {'statusCode': 500, 'body': 'Internal server error'}