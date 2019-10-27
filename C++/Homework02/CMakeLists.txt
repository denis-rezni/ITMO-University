cmake_minimum_required(VERSION 3.14)
project(Homework02)

set(CMAKE_CXX_STANDARD 17)

add_executable(Homework02 deque.hpp randomized_queue.hpp
        subset.cpp)
#deque_and_queue_test.cpp