#!/usr/bin/env python

import pika

connection = pika.BlockingConnection(pika.ConnectionParameters(host='192.168.1.2'))


channel = connection.channel()

channel.queue_declare(queue='hello')

def callback(ch, method, properties, body):
  print "[x]Receive %r" % body


channel.basic_consume(
  queue = 'hello', on_message_callback = callback, auto_ack = True)

print ' [*] Waiting for messages. to exit  press CTRL + C' 

channel.start_consuming() 
