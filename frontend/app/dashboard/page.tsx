"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"  // ← This line fixes the error
import { motion } from "framer-motion"
import { DollarSign, BedDouble, Calendar, Users } from "lucide-react"

export default function DashboardPage() {
    return (
        <div className="space-y-12">
            <motion.h1
                initial={{ opacity: 0, y: 30 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.8 }}
                className="text-4xl font-bold text-white gradient-text"
            >
                Welcome to the Dashboard
            </motion.h1>

            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
                {[
                    { title: "Total Revenue", value: "$48,250", change: "+12.5%", icon: DollarSign, color: "text-green-400" },
                    { title: "Occupied Rooms", value: "124 / 200", change: "62%", icon: BedDouble, color: "text-red-400" },
                    { title: "Bookings Today", value: "38", change: "+8", icon: Calendar, color: "text-blue-400" },
                    { title: "Active Guests", value: "1,892", change: "+5.2%", icon: Users, color: "text-purple-400" },
                ].map((stat, index) => (
                    <motion.div
                        key={stat.title}
                        initial={{ opacity: 0, y: 30 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6, delay: index * 0.1 }}
                    >
                        <Card className="glass hover:shadow-[0_0_40px_rgba(255,255,255,0.15)] transition-all duration-300 h-full">
                            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                                <CardTitle className="text-sm font-medium text-white/80">
                                    {stat.title}
                                </CardTitle>
                                <stat.icon className={`h-6 w-6 ${stat.color}`} />
                            </CardHeader>
                            <CardContent>
                                <div className="text-3xl font-bold text-white">{stat.value}</div>
                                <p className={`text-xs ${stat.color} mt-1 font-medium`}>{stat.change}</p>
                            </CardContent>
                        </Card>
                    </motion.div>
                ))}
            </div>

            {/* Recent Activity */}
            <motion.div
                initial={{ opacity: 0, y: 40 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.8, delay: 0.4 }}
            >
                <Card className="glass">
                    <CardHeader>
                        <CardTitle className="text-2xl text-white">Recent Activity</CardTitle>
                    </CardHeader>
                    <CardContent>
                        <div className="space-y-6">
                            {[
                                { time: "2 min ago", action: "New booking #BK045 – John Doe" },
                                { time: "15 min ago", action: "Room A101 checked out" },
                                { time: "45 min ago", action: "Payment received $450" },
                            ].map((item, index) => (
                                <div key={index} className="flex items-start gap-4 border-b border-white/10 pb-4 last:border-0 last:pb-0">
                                    <div className="flex-1">
                                        <p className="text-base font-medium text-white">{item.action}</p>
                                    </div>
                                    <span className="text-sm text-white/60 whitespace-nowrap">{item.time}</span>
                                </div>
                            ))}
                        </div>
                    </CardContent>
                </Card>
            </motion.div>

            {/* Quick Actions */}
            <motion.div
                initial={{ opacity: 0, y: 40 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.8, delay: 0.6 }}
                className="grid grid-cols-1 sm:grid-cols-3 gap-6"
            >
                <Button className="glass py-8 text-lg font-medium rounded-xl border border-white/20 hover:border-white/50 transition-all shadow-xl hover:shadow-[0_0_40px_rgba(30,218,255,0.4)]">
                    Add New Room
                </Button>
                <Button className="glass py-8 text-lg font-medium rounded-xl border border-white/20 hover:border-white/50 transition-all shadow-xl hover:shadow-[0_0_40px_rgba(0,250,154,0.4)]">
                    Create Booking
                </Button>
                <Button className="glass py-8 text-lg font-medium rounded-xl border border-white/20 hover:border-white/50 transition-all shadow-xl hover:shadow-[0_0_40px_rgba(255,215,0,0.4)]">
                    Record Payment
                </Button>
            </motion.div>
        </div>
    )
}