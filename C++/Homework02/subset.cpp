//
// Created by denis on 03.06.2019.
//
#include <iostream>
#include "randomized_queue.hpp"

int main(int number) {
    randomized_queue<std::string> queue;
    std::string cur;
    while(std::cin >> cur){
        queue.enqueue(cur);
    }
    if(number > queue.size()){
        std::cout << "number (\'k\') is too big" << std::endl;
        return 0;
    }
    auto it = queue.begin();
    while(it != queue.end()){
        std::cout << *it << std::endl;
        ++it;
    }
    return 0;
}
