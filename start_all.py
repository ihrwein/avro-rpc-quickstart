#!/usr/bin/python

import sys
from math import log
from subprocess import call, STDOUT, PIPE

class CommandGenerator:

    def __init__(self,
                 impl,
                 ipaddr="127.0.0.1",
                 port="6000",
                 msgnum="1000"
                 ):
        self.impl = impl
        self.ipaddr = ipaddr
        self.port = port
        self.msgnum = msgnum
        self.format_string = " -threads {0} -msgsize {1}"
        self.const_format_string = ("bash start.sh -client {0}"
                                    " -port {1}"
                                    " -msgnum {2}"
                                    " -impl {3}"
                                    ).format(ipaddr, port, msgnum, impl)
                                      
    def create_test_command(self, thread_num, msg_size):
        return self.const_format_string + self.format_string.format(thread_num,
                                                                    msg_size)

impl = None

if len(sys.argv) != 2:
    impl = "http"
else:
    impl = sys.argv[1]

min_msgsize=32
msgsize_range_interval=11

min_threads=1
thread_range_interval=5

thread_range_start = int(log(min_threads, 2))
thread_range_end = thread_range_start + thread_range_interval

msgsize_range_start = int(log(min_msgsize, 2))
msgsize_range_end = msgsize_range_start + msgsize_range_interval

thread_generator = (2**x for x in range(thread_range_start,
                                        thread_range_end))

cmd_gen = CommandGenerator(impl)
for thread in thread_generator:
   
    msgsize_generator = (2**x for x in range(msgsize_range_start,
                                             msgsize_range_end)) 
    for msgsize in msgsize_generator:
        command = cmd_gen.create_test_command(thread, msgsize)
        with open("/dev/null", "wb") as DEVNULL:
            p = call(command, stdout=DEVNULL, shell=True)
