//
// Created by denis on 26.05.2019.
//
#include <utility>
#include "term.hpp"
#include <utility>
#include <string>
#include <iostream>
#include <functional>


int term::getWeight() const {
    return weight;
}

const std::string& term::getString() const {
    return str;
}

term::term(int w, std::string s) {
    weight = w;
    str = std::move(s);
}

std::string term::operator()() const{
    return this->toString();
}

bool operator==(const term &a, const term &b) {
    return a.str == b.str && a.weight == b.weight;
}

bool operator!=(const term &a, const term &b) {
    return !(a == b);
}

bool operator<(const term &a, const term &b) {
    if (a.str < b.str) {
        return true;
    } else if (a.str == b.str) {
        return a.weight < b.weight;
    }
    return false;
}

bool operator>(const term &a, const term &b) {
    return b < a;
}

bool operator<=(const term &a, const term &b) {
    return a < b || a == b;
}

bool operator>=(const term &a, const term &b) {
    return a > b || a == b;
}

std::string term::toString() const{
    return std::to_string(weight) + " " + str;
}


std::function<bool(const term &, const term &)> term::by_reverse_weight_order() {
    return [](const term &t1, const term &t2) {
        return t1.getWeight() > t2.getWeight();
    };
};

std::function<bool(const term &, const term &)> term::by_prefix_order(int r) {
    return [r](const term &t1, const term &t2) {
        std::string s1 = t1.getString().substr(0, r);
        std::string s2 = t2.getString().substr(0, r);
        return s1 < s2;
    };
};

bool term::compareTerms(term &t1, term &t2,
                        const std::function<bool(term &, term &)>& func){
    return func(t1, t2);
}

