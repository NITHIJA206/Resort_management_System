"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Plus } from "lucide-react"
import { motion } from "framer-motion"

const placeholderBookings = [
    { id: 1, bookingId: "BK001", user: "John Doe", room: "A101", checkIn: "2026-02-10", checkOut: "2026-02-15", status: "CONFIRMED", amount: 1250 },
    { id: 2, bookingId: "BK002", user: "Jane Smith", room: "B205", checkIn: "2026-02-12", checkOut: "2026-02-14", status: "PENDING", amount: 900 },
    { id: 3, bookingId: "BK003", user: "Mike Lee", room: "C310", checkIn: "2026-02-20", checkOut: "2026-02-25", status: "CANCELLED", amount: 750 },
    { id: 4, bookingId: "BK004", user: "Sara Kim", room: "V401", checkIn: "2026-02-15", checkOut: "2026-02-20", status: "CONFIRMED", amount: 2600 },
]

export default function BookingSystemPage() {
    return (
        <div className="space-y-12">
            <div className="flex justify-between items-center">
                <h1 className="text-4xl font-bold text-white gradient-text">Booking System</h1>
                <Button className="glass px-8 py-4 text-lg font-medium rounded-full border border-white/30 hover:border-white/60 transition-all shadow-xl hover:shadow-[0_0_40px_rgba(30,218,255,0.4)]">
                    <Plus className="mr-2 h-5 w-5" /> New Booking
                </Button>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                {placeholderBookings.map((booking, index) => (
                    <motion.div
                        key={booking.id}
                        initial={{ opacity: 0, y: 30 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6, delay: index * 0.1 }}
                    >
                        <Card className="glass overflow-hidden hover:shadow-[0_0_40px_rgba(255,255,255,0.15)] transition-all duration-300">
                            <CardHeader className="pb-3">
                                <div className="flex justify-between items-start">
                                    <CardTitle className="text-2xl font-semibold text-white">
                                        {booking.bookingId}
                                    </CardTitle>
                                    <Badge
                                        variant="outline"
                                        className={
                                            booking.status === "CONFIRMED"
                                                ? "bg-green-500/20 text-green-300 border-green-500/40"
                                                : booking.status === "PENDING"
                                                    ? "bg-yellow-500/20 text-yellow-300 border-yellow-500/40"
                                                    : "bg-red-500/20 text-red-300 border-red-500/40"
                                        }
                                    >
                                        {booking.status}
                                    </Badge>
                                </div>
                                <p className="text-base text-white/80 mt-1">
                                    {booking.user} • Room {booking.room}
                                </p>
                            </CardHeader>
                            <CardContent className="pt-4 space-y-4">
                                <div className="flex justify-between items-center">
                                    <span className="text-lg font-medium text-white/90">Amount:</span>
                                    <span className="text-2xl font-bold text-white">
                    ${booking.amount}
                  </span>
                                </div>
                                <div className="flex gap-6 text-sm text-white/70">
                                    <span>Check-in: {booking.checkIn}</span>
                                    <span>Check-out: {booking.checkOut}</span>
                                </div>
                            </CardContent>
                        </Card>
                    </motion.div>
                ))}
            </div>
        </div>
    )
}