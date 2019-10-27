#include <iostream>
#include <cassert>
#include "deque.hpp"
#include "randomized_queue.hpp"

int main() {
    deque<int> deq;
    for (int i = 0; i < 10; i++) {
        deq.push_front(i);
    }
    deque<int>::iterator it;
    for (it = deq.begin(); it != deq.end(); ++it) {
        std::cout << *it << std::endl;
    }


    randomized_queue<int> queue;
    for (int i = 0; i < 10; i++) {
        queue.enqueue(i);
    }
    std::cout << "size: " << queue.size() << std::endl;
    for (int i = 0; i < 3; i++) {
        std::cout << "sample: " << queue.sample() << std::endl;
    }
    for (int i = 0; i < 3; i++) {
        std::cout << "dequeue: " << queue.dequeue() << std::endl;
    }
    std::cout << "size: " << queue.size() << std::endl;
    for (int i = 0; i < 3; i++) {
        std::cout << "sample: " << queue.sample() << std::endl;
    }
    randomized_queue<int>::iterator q_it = queue.begin();
    for(q_it = queue.begin(); q_it != queue.end(); ++q_it){
        std::cout << *q_it << std::endl;
    }

    std::cout << "vectors" << std::endl;
    auto b1 = queue.begin();
    auto e1 = queue.end();
    auto b2 = queue.begin();
    auto e2 = queue.end();

    std::vector<int> v11, v12;
    for(auto b = b1; b != e1; ++b){
        v11.push_back(*b);
    }

    std::cout << "v11: ";
    for(int i : v11){
        std::cout << i << ' ';
    }
    std::cout << std::endl;
    for(auto b = b1; b != e1; ++b){
        v12.push_back(*b);
    }
    std::cout << "v12: ";
    for(int i : v12){
        std::cout << i << ' ';
    }
    std::cout << std::endl;
    assert(v11 == v12);

    std::vector<int> v21, v22;
    for(auto b = b2; b != e2; ++b){
        v21.push_back(*b);
    }
    std::cout << "v21: ";
    for(int i : v21){
        std::cout << i << ' ';
    }
    std::cout << std::endl;
    for(auto b = b2; b != e2; ++b){
        v22.push_back(*b);
    }
    std::cout << "v22: ";
    for(int i : v22){
        std::cout << i << ' ';
    }
    std::cout << std::endl;
    assert(v21 == v22);
    assert(v11 != v21);

    while (!queue.empty()) {
        std::cout << queue.dequeue() << ' ';
    }

}