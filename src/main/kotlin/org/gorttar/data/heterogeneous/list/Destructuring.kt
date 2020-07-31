@file:Suppress("NOTHING_TO_INLINE")

package org.gorttar.data.heterogeneous.list

// component1

@JvmName("a1")
inline operator fun <A> HList1<A>.component1(): A = tail

@JvmName("a2")
inline operator fun <A> HList2<A, *>.component1(): A = head.component1()

@JvmName("a3")
inline operator fun <A> HList3<A, *, *>.component1(): A = head.component1()

@JvmName("a4")
inline operator fun <A> HList4<A, *, *, *>.component1(): A = head.component1()

@JvmName("a5")
inline operator fun <A> HList5<A, *, *, *, *>.component1(): A = head.component1()

@JvmName("a6")
inline operator fun <A> HList6<A, *, *, *, *, *>.component1(): A = head.component1()

@JvmName("a7")
inline operator fun <A> HList7<A, *, *, *, *, *, *>.component1(): A = head.component1()

@JvmName("a8")
inline operator fun <A> HList8<A, *, *, *, *, *, *, *>.component1(): A = head.component1()

@JvmName("a9")
inline operator fun <A> HList9<A, *, *, *, *, *, *, *, *>.component1(): A = head.component1()

@JvmName("a10")
inline operator fun <A> HList10<A, *, *, *, *, *, *, *, *, *>.component1(): A = head.component1()

// component2

@JvmName("b2")
inline operator fun <B> HList2<*, B>.component2(): B = tail

@JvmName("b3")
inline operator fun <B> HList3<*, B, *>.component2(): B = head.component2()

@JvmName("b4")
inline operator fun <B> HList4<*, B, *, *>.component2(): B = head.component2()

@JvmName("b5")
inline operator fun <B> HList5<*, B, *, *, *>.component2(): B = head.component2()

@JvmName("b6")
inline operator fun <B> HList6<*, B, *, *, *, *>.component2(): B = head.component2()

@JvmName("b7")
inline operator fun <B> HList7<*, B, *, *, *, *, *>.component2(): B = head.component2()

@JvmName("b8")
inline operator fun <B> HList8<*, B, *, *, *, *, *, *>.component2(): B = head.component2()

@JvmName("b9")
inline operator fun <B> HList9<*, B, *, *, *, *, *, *, *>.component2(): B = head.component2()

@JvmName("b10")
inline operator fun <B> HList10<*, B, *, *, *, *, *, *, *, *>.component2(): B = head.component2()

// component3

@JvmName("c3")
inline operator fun <C> HList3<*, *, C>.component3(): C = tail

@JvmName("c4")
inline operator fun <C> HList4<*, *, C, *>.component3(): C = head.component3()

@JvmName("c5")
inline operator fun <C> HList5<*, *, C, *, *>.component3(): C = head.component3()

@JvmName("c6")
inline operator fun <C> HList6<*, *, C, *, *, *>.component3(): C = head.component3()

@JvmName("c7")
inline operator fun <C> HList7<*, *, C, *, *, *, *>.component3(): C = head.component3()

@JvmName("c8")
inline operator fun <C> HList8<*, *, C, *, *, *, *, *>.component3(): C = head.component3()

@JvmName("c9")
inline operator fun <C> HList9<*, *, C, *, *, *, *, *, *>.component3(): C = head.component3()

@JvmName("c10")
inline operator fun <C> HList10<*, *, C, *, *, *, *, *, *, *>.component3(): C = head.component3()

// component4

@JvmName("d4")
inline operator fun <D> HList4<*, *, *, D>.component4(): D = tail

@JvmName("d5")
inline operator fun <D> HList5<*, *, *, D, *>.component4(): D = head.component4()

@JvmName("d6")
inline operator fun <D> HList6<*, *, *, D, *, *>.component4(): D = head.component4()

@JvmName("d7")
inline operator fun <D> HList7<*, *, *, D, *, *, *>.component4(): D = head.component4()

@JvmName("d8")
inline operator fun <D> HList8<*, *, *, D, *, *, *, *>.component4(): D = head.component4()

@JvmName("d9")
inline operator fun <D> HList9<*, *, *, D, *, *, *, *, *>.component4(): D = head.component4()

@JvmName("d10")
inline operator fun <D> HList10<*, *, *, D, *, *, *, *, *, *>.component4(): D = head.component4()

// component5

@JvmName("e5")
inline operator fun <E> HList5<*, *, *, *, E>.component5(): E = tail

@JvmName("e6")
inline operator fun <E> HList6<*, *, *, *, E, *>.component5(): E = head.component5()

@JvmName("e7")
inline operator fun <E> HList7<*, *, *, *, E, *, *>.component5(): E = head.component5()

@JvmName("e8")
inline operator fun <E> HList8<*, *, *, *, E, *, *, *>.component5(): E = head.component5()

@JvmName("e9")
inline operator fun <E> HList9<*, *, *, *, E, *, *, *, *>.component5(): E = head.component5()

@JvmName("e10")
inline operator fun <E> HList10<*, *, *, *, E, *, *, *, *, *>.component5(): E = head.component5()

// component6

@JvmName("f6")
inline operator fun <F> HList6<*, *, *, *, *, F>.component6(): F = tail

@JvmName("f7")
inline operator fun <F> HList7<*, *, *, *, *, F, *>.component6(): F = head.component6()

@JvmName("f8")
inline operator fun <F> HList8<*, *, *, *, *, F, *, *>.component6(): F = head.component6()

@JvmName("f9")
inline operator fun <F> HList9<*, *, *, *, *, F, *, *, *>.component6(): F = head.component6()

@JvmName("f10")
inline operator fun <F> HList10<*, *, *, *, *, F, *, *, *, *>.component6(): F = head.component6()

// component7

@JvmName("g7")
inline operator fun <G> HList7<*, *, *, *, *, *, G>.component7(): G = tail

@JvmName("g8")
inline operator fun <G> HList8<*, *, *, *, *, *, G, *>.component7(): G = head.component7()

@JvmName("g9")
inline operator fun <G> HList9<*, *, *, *, *, *, G, *, *>.component7(): G = head.component7()

@JvmName("g10")
inline operator fun <G> HList10<*, *, *, *, *, *, G, *, *, *>.component7(): G = head.component7()

// component8

@JvmName("h8")
inline operator fun <H> HList8<*, *, *, *, *, *, *, H>.component8(): H = tail

@JvmName("h9")
inline operator fun <H> HList9<*, *, *, *, *, *, *, H, *>.component8(): H = head.component8()

@JvmName("h10")
inline operator fun <H> HList10<*, *, *, *, *, *, *, H, *, *>.component8(): H = head.component8()

// component9

@JvmName("i9")
inline operator fun <I> HList9<*, *, *, *, *, *, *, *, I>.component9(): I = tail

@JvmName("i10")
inline operator fun <I> HList10<*, *, *, *, *, *, *, *, I, *>.component9(): I = head.component9()

// component10

@JvmName("j10")
inline operator fun <J> HList10<*, *, *, *, *, *, *, *, *, J>.component10(): J = tail
