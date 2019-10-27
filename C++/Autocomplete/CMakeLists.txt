cmake_minimum_required(VERSION 3.14)
project(Autocomplete)

set(CMAKE_CXX_STANDARD 17)

add_executable(Autocomplete term.cpp term.hpp binary_search_deluxe.cpp binary_search_deluxe.hpp autocomplete.cpp autocomplete.hpp autocomplete_test.cpp autocomplete_test.hpp)