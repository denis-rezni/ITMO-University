//
// Created by denis on 01.06.2019.
//

#ifndef HOMEWORK02_RANDOMIZED_QUEUE_HPP
#define HOMEWORK02_RANDOMIZED_QUEUE_HPP


#include <vector>
#include <random>
#include <chrono>
#include <algorithm>

template<typename T>
class randomized_queue_iterator;

template<typename T>
class randomized_queue {
private:
    std::vector<T> vec;
    std::mt19937_64 g;
    size_t length = 0;

    int next_random_pos(int bound) {
        return std::uniform_int_distribution<int>(0, bound - 1)(g);
    }

public:
    typedef randomized_queue_iterator<T> iterator;

    friend class randomized_queue_iterator<T>;

    randomized_queue() : g((unsigned) std::chrono::steady_clock::now().time_since_epoch().count()) {};

    bool empty() const { return length == 0; };

    std::size_t size() const { return length; };

    void enqueue(T t) {
        if (length < vec.size()) {
            vec[length] = t;
        } else {
            vec.push_back(t);
        }
        length++;
    }

    T sample() {
        int pos = next_random_pos(length);
        return vec[pos];
    }

    T dequeue() {
        size_t pos = next_random_pos(length);
        std::swap(vec[pos], vec[length - 1]);
        length--;
        return vec[length];
    }

    iterator begin() { return iterator(*this, 0); }
е
    iterator end() { return iterator(*this, length); }


};

template<typename T>
class randomized_queue_iterator {
private:
    randomized_queue<T> &queue;
    std::vector<size_t> indexes;
    size_t position;

public:

    randomized_queue_iterator(randomized_queue<T> &to_iterate, size_t pos) : queue(to_iterate), indexes(queue.length),
                                                                             position(pos) {
        for (int i = 0; i < queue.length; ++i) {
            indexes[i] = i;
        }
        std::shuffle(indexes.begin(), indexes.end(), queue.g);
    }


    bool operator==(const randomized_queue_iterator &i) const {
        return &i.queue.vec == &queue.vec && i.position == position;
    }

    bool operator!=(const randomized_queue_iterator &i) const {
        return !(*this == i);
    }

    randomized_queue_iterator &operator++() {
        if(position + 1 < queue.size()){
            ++position;
        }
        return *this;
    }

    randomized_queue_iterator &operator--() {
        if(position - 1 >= 0){
            position--;
        }
        return &this;
    }

    randomized_queue_iterator &operator+=(int delta) {
        if(position + delta < queue.size()){
            position += delta;
        }
        return *this;
    }

    randomized_queue_iterator &operator-=(int delta) {
        if(position - delta >= 0){
            position -= delta;
        }
        return *this;
    }

    randomized_queue_iterator &operator=(const randomized_queue_iterator &it) {
        if (this == &it) {
            return *this;
        }
        queue = it.queue;
        indexes = it.indexes;
        position = it.position;
        return *this;
    }

    T &operator*() const {
        return queue.vec[indexes[position]];
    }

};


#endif //HOMEWORK02_RANDOMIZED_QUEUE_HPP
