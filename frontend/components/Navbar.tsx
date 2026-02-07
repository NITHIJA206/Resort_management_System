"use client"

import Link from "next/link"
import { usePathname } from "next/navigation"
import { motion } from "framer-motion"
import { Button } from "@/components/ui/button"
import { Menu } from "lucide-react"
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet"

const navLinks = [
    { name: "Home", href: "/" },
    { name: "Rooms", href: "/dashboard/room-management" },
    { name: "Bookings", href: "/dashboard/booking-system" },
    { name: "Payments", href: "/dashboard/payment-services" },
    { name: "Users", href: "/dashboard/user-management" },
]

export default function Navbar() {
    const pathname = usePathname()

    const NavContent = () => (
        <div className="flex items-center gap-8">
            {navLinks.map((link) => (
                <Link
                    key={link.name}
                    href={link.href}
                    className={`text-sm font-medium transition-colors ${
                        pathname === link.href
                            ? "text-white"
                            : "text-white/80 hover:text-white"
                    }`}
                >
                    {link.name}
                </Link>
            ))}
        </div>
    )

    return (
        <motion.nav
            initial={{ y: -100 }}
            animate={{ y: 0 }}
            transition={{ duration: 0.6, ease: "easeOut" }}
            className="fixed top-0 left-0 right-0 z-50 backdrop-blur-xl border-b border-white/10 glass px-6 py-4"
        >
            <div className="max-w-7xl mx-auto flex items-center justify-between">
                <Link href="/" className="text-2xl font-bold gradient-text tracking-tight">
                    ResortPro
                </Link>

                <div className="hidden md:flex items-center gap-12">
                    <NavContent />
                </div>

                <div className="flex items-center gap-4">
                    <Button className="glass px-6 py-2 text-sm font-medium rounded-full border border-white/20 hover:border-white/40 transition-all shadow-lg">
                        Login
                    </Button>

                    <Sheet>
                        <SheetTrigger asChild className="md:hidden">
                            <Button variant="ghost" size="icon" className="text-white">
                                <Menu className="h-6 w-6" />
                            </Button>
                        </SheetTrigger>
                        <SheetContent side="right" className="w-80 glass border-l border-white/10">
                            <div className="flex flex-col gap-6 mt-12">
                                <NavContent />
                                <Button className="glass w-full py-6 text-base font-medium">
                                    Login
                                </Button>
                            </div>
                        </SheetContent>
                    </Sheet>
                </div>
            </div>
        </motion.nav>
    )
}