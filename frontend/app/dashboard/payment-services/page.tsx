"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Plus } from "lucide-react"
import { motion } from "framer-motion"

const placeholderPayments = [
    { id: 1, paymentId: "PAY001", bookingId: "BK001", amount: 1250, method: "CREDIT CARD", status: "PAID", date: "2026-02-08" },
    { id: 2, paymentId: "PAY002", bookingId: "BK002", amount: 900, method: "CASH", status: "PENDING", date: "2026-02-09" },
    { id: 3, paymentId: "PAY003", bookingId: "BK003", amount: 750, method: "ONLINE TRANSFER", status: "FAILED", date: "2026-02-10" },
    { id: 4, paymentId: "PAY004", bookingId: "BK004", amount: 2600, method: "CREDIT CARD", status: "PAID", date: "2026-02-11" },
]

export default function PaymentServicesPage() {
    return (
        <div className="space-y-12">
            <div className="flex justify-between items-center">
                <h1 className="text-4xl font-bold text-white gradient-text">Payment Services</h1>
                <Button className="glass px-8 py-4 text-lg font-medium rounded-full border border-white/30 hover:border-white/60 transition-all shadow-xl hover:shadow-[0_0_40px_rgba(30,218,255,0.4)]">
                    <Plus className="mr-2 h-5 w-5" /> Record Payment
                </Button>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                {placeholderPayments.map((payment, index) => (
                    <motion.div
                        key={payment.id}
                        initial={{ opacity: 0, y: 30 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6, delay: index * 0.1 }}
                    >
                        <Card className="glass overflow-hidden hover:shadow-[0_0_40px_rgba(255,255,255,0.15)] transition-all duration-300">
                            <CardHeader className="pb-3">
                                <div className="flex justify-between items-start">
                                    <CardTitle className="text-2xl font-semibold text-white">
                                        {payment.paymentId}
                                    </CardTitle>
                                    <Badge
                                        variant="outline"
                                        className={
                                            payment.status === "PAID"
                                                ? "bg-green-500/20 text-green-300 border-green-500/40"
                                                : payment.status === "PENDING"
                                                    ? "bg-yellow-500/20 text-yellow-300 border-yellow-500/40"
                                                    : "bg-red-500/20 text-red-300 border-red-500/40"
                                        }
                                    >
                                        {payment.status}
                                    </Badge>
                                </div>
                                <p className="text-base text-white/80 mt-1">
                                    Booking #{payment.bookingId}
                                </p>
                            </CardHeader>
                            <CardContent className="pt-4 space-y-4">
                                <div className="flex justify-between items-center">
                                    <span className="text-lg font-medium text-white/90">Amount:</span>
                                    <span className="text-2xl font-bold text-white">
                    ${payment.amount}
                  </span>
                                </div>
                                <div className="flex gap-6 text-sm text-white/70">
                                    <span>Method: {payment.method}</span>
                                    <span>Date: {payment.date}</span>
                                </div>
                            </CardContent>
                        </Card>
                    </motion.div>
                ))}
            </div>
        </div>
    )
}