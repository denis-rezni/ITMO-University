//
// Created by denis on 25.05.2019.
//

#ifndef AUTOCOMPLETE_TERM_HPP
#define AUTOCOMPLETE_TERM_HPP

#include <string>
#include <functional>

class term {
private:
    int weight;
    std::string str;
public:
    int getWeight() const;

    const std::string& getString() const;

    term(int w, std::string s);

    std::string toString() const;

    friend bool operator==(const term &a, const term &b);

    friend bool operator!=(const term &a, const term &b);

    friend bool operator>(const term &a, const term &b);

    friend bool operator<(const term &a, const term &b);

    friend bool operator<=(const term &a, const term &b);

    friend bool operator>=(const term &a, const term &b);

    std::string operator()() const;

    static std::function<bool(const term &, const term &)> by_reverse_weight_order();

    static std::function<bool(const term &, const term &)> by_prefix_order(int r);

    static bool compareTerms(term &t1, term &t2, const std::function<bool(term &, term &)>& func);
};


#endif //AUTOCOMPLETE_TERM_HPP
