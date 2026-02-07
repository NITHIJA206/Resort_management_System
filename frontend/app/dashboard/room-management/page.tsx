"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Plus } from "lucide-react"
import { motion } from "framer-motion"

const placeholderRooms = [
    { id: 1, roomNumber: "A101", type: "Deluxe Ocean View", status: "AVAILABLE", price: 250, floor: 1, building: "Main Block" },
    { id: 2, roomNumber: "B205", type: "Premium Suite", status: "OCCUPIED", price: 450, floor: 2, building: "Villa Wing" },
    { id: 3, roomNumber: "C310", type: "Standard", status: "MAINTENANCE", price: 150, floor: 3, building: "East Tower" },
    { id: 4, roomNumber: "V401", type: "Family Villa", status: "AVAILABLE", price: 650, floor: 4, building: "Private Villas" },
]

export default function RoomManagementPage() {
    return (
        <div className="space-y-12">
            <div className="flex justify-between items-center">
                <h1 className="text-4xl font-bold text-white gradient-text">Room Management</h1>
                <Button className="glass px-8 py-4 text-lg font-medium rounded-full border border-white/30 hover:border-white/60 transition-all shadow-xl hover:shadow-[0_0_40px_rgba(30,218,255,0.4)]">
                    <Plus className="mr-2 h-5 w-5" /> Add New Room
                </Button>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                {placeholderRooms.map((room, index) => (
                    <motion.div
                        key={room.id}
                        initial={{ opacity: 0, y: 30 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.6, delay: index * 0.1 }}
                    >
                        <Card className="glass overflow-hidden hover:shadow-[0_0_40px_rgba(255,255,255,0.15)] transition-all duration-300">
                            <CardHeader className="pb-3">
                                <div className="flex justify-between items-start">
                                    <CardTitle className="text-2xl font-semibold text-white">
                                        {room.roomNumber}
                                    </CardTitle>
                                    <Badge
                                        variant="outline"
                                        className={
                                            room.status === "AVAILABLE"
                                                ? "bg-green-500/20 text-green-300 border-green-500/40"
                                                : room.status === "OCCUPIED"
                                                    ? "bg-red-500/20 text-red-300 border-red-500/40"
                                                    : "bg-yellow-500/20 text-yellow-300 border-yellow-500/40"
                                        }
                                    >
                                        {room.status}
                                    </Badge>
                                </div>
                                <p className="text-base text-white/80 mt-1">{room.type}</p>
                            </CardHeader>
                            <CardContent className="pt-4 space-y-4">
                                <div className="flex justify-between items-center">
                                    <span className="text-lg font-medium text-white/90">Price:</span>
                                    <span className="text-2xl font-bold text-white">
                    ${room.price}
                  </span>
                                </div>
                                <div className="flex gap-6 text-sm text-white/70">
                                    <span>Floor: {room.floor}</span>
                                    <span>Building: {room.building}</span>
                                </div>
                            </CardContent>
                        </Card>
                    </motion.div>
                ))}
            </div>
        </div>
    )
}